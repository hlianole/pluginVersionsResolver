# Plugin Storage Simulator

Simulates a service for receiving and publishing plugins. 
Each plugin has different versions, and each version may have compatibility 
options for different operating systems/architectures.

## Usage:
First you will have to run application:
- From IDE
- Manually: In `pluginVersionResolver` directory run 
```bash
./gradlew build
```
```
./gradlew run
```

Port: 8080

API definition is [PluginApi.kt](src/main/kotlin/com/hlianole/jetbrains/internship/pluginVersionsResolver/controller/PluginApi.kt)