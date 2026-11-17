name: Gerar APK StreamGo
on: [push, workflow_dispatch]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - name: Configurar Java 17
        uses: actions/setup-java@v4
        with:
          distribution: 'temurin'
          java-version: '17'
      - name: Compilar APK (usando Gradle direto)
        uses: gradle/gradle-build-action@v3
        with:
          gradle-version: 8.6
          arguments: assembleDebug
      - name: Enviar APK para Download
        uses: actions/upload-artifact@v4
        with:
          name: StreamGo-APK-Instalavel
          path: app/build/outputs/apk/debug/app-debug.apk
