class SecurityManager @Inject constructor(@ApplicationContext ctx: Context) {
    private val masterKey = MasterKey.Builder(ctx).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
    private val prefs = EncryptedSharedPreferences.create(ctx, "streamgo_keys", masterKey, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)
    
    fun salvarKey(plataforma: String, key: String) = prefs.edit().putString(plataforma, key).apply()
    fun getKey(plataforma: String): String? = prefs.getString(plataforma, null)
    // NUNCA salvar keys no código fonte ou em SharedPreferences sem criptografia
}
