package controlCenter.domain.useCases


data class ControlCenterUseCases(
    val fetchComponentsData: FetchComponentsData,
    val getRemoteData: GetRemoteData,
    val onComponent: OnInterActiveClick
)
