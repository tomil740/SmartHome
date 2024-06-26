package controlCenter.presentation.controlCenterScreen

sealed class ControlCenterEvents {
    data class OnInteractiveComponent(val nameId:String):ControlCenterEvents()
}