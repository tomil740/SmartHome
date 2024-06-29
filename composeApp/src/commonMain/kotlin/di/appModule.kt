package di

import controlCenter.data.remoteDb.RemoteDbFactories
import controlCenter.data.repository.RepositoryImpl
import controlCenter.domain.repository.Repository
import controlCenter.domain.useCases.ControlCenterUseCases
import controlCenter.domain.useCases.FetchComponentsData
import controlCenter.domain.useCases.GetConnectionState
import controlCenter.domain.useCases.GetRemoteData
import controlCenter.domain.useCases.OnInterActiveClick
import controlCenter.presentation.controlCenterScreen.ControlCenterViewmodel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl(get()) }
    single {
        ControlCenterUseCases(
            getRemoteData = GetRemoteData(get()),
            fetchComponentsData = FetchComponentsData(),
            onComponent = OnInterActiveClick(get()),
            getConnectionState = GetConnectionState(get())
        )
    }
    factory { ControlCenterViewmodel(get()) }

    single {
        RemoteDbFactories().startDb()
    }

}