class UvcCaptureManager @Inject constructor(private val usbManager: UsbManager) {
    fun detectarPlacas(): List<UsbDevice> = usbManager.deviceList.values.filter { dev ->
        dev.getInterface(0)?.interfaceClass == UsbConstants.USB_CLASS_VIDEO // 14 = UVC
    }
    
    fun listarResolucoesSuportadas(device: UsbDevice): List<Size> {
        // Usa libuvccamera para query real do descritor UVC
        return UVCCamera.getSupportedPreviewSizes(device) // retorna apenas formatos reais
    }
    
    fun registrarReceiver(context: Context) {
        context.registerReceiver(usbReceiver, IntentFilter(UsbManager.ACTION_USB_DEVICE_DETACHED))
        context.registerReceiver(usbReceiver, IntentFilter(ACTION_USB_PERMISSION))
    }
}
