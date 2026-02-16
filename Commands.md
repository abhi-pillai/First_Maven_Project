# Commands Used for My First Maven Project

This file documents all the terminal commands used to **create, build, and run** the Maven project for MP3/MP4 playback.

---

## 1. Create a Maven Project

1. Open a terminal and navigate to your project folder:
```cmd
cd First_Maven_Project
```

2. Generate a Maven project using the quickstart archetype:

```cmd
mvn archetype:generate
```

When prompted:

- **Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains):** `2309`
  - Select `org.apache.maven.archetypes:maven-archetype-quickstart`
- **Choose version:**
  - Choose a number: `9` (version 1.5)
- Enter `groupId`, `artifactId`, `package` as needed (e.g., `com.example`, `my-first-app`).

---

## 2. Navigate to the Project Directory

```cmd
cd First_Maven_Project\my-first-app
```

---

## 3. Verify Maven Project Build

```cmd
mvn clean compile
```

- Cleans previous builds and compiles Java code.
- Detects POM or dependency errors.

---

## 4. Run the Default "Hello World" App

```cmd
mvn exec:java
```

- Runs the main class specified in `pom.xml`.
- Output:

```
Hello World!
```

**Note:** If `mainClass` is missing, you get an error. Fix by adding `<mainClass>` in `exec-maven-plugin` in POM.

---

## 5. Set Environment Variables (Windows)

### Current Session (Recommended)

Set environment variables in the **current terminal session**:

**Command Prompt:**
```cmd
set MP3_PATH=D:\media\song.mp3
set MP4_PATH=D:\media\video.mp4
```

**PowerShell:**
```powershell
$env:MP3_PATH="D:\media\song.mp3"
$env:MP4_PATH="D:\media\video.mp4"
```

### Persistent (Less Reliable)

⚠️ This approach often caused issues because the terminal session may not see the updated variable.

```cmd
setx MP3_PATH D:\media\song.mp3
setx MP4_PATH D:\media\video.mp4
```

- Requires closing and reopening the terminal.
- Verified with:

```cmd
echo %MP3_PATH%
echo %MP4_PATH%
```

---

## 6. Run JavaFX App with Environment Variables

After setting environment variables in the current session:

```cmd
mvn clean javafx:run
```

The app will prompt:

```
Choose what to play:
1 - MP3
2 - MP4
```

- Enter `1` for audio, `2` for video.

---

## 7. Optional: Run with Hardcoded File Paths (for testing)

```cmd
mvn clean javafx:run
```

- Works if you hardcode the paths in `App.java`.
- Not recommended for production; environment variables or a file chooser are preferred.

---

## 8. Verify Environment Variables

### Windows environment variables:

**Command Prompt:**
```cmd
echo %MP3_PATH%
echo %MP4_PATH%
```

**PowerShell:**
```powershell
echo $env:MP3_PATH
echo $env:MP4_PATH
```

### Linux/Mac:
```bash
echo $MP3_PATH
echo $MP4_PATH
```

### Java environment variables (inside Maven / JavaFX JVM):

```java
System.getenv("MP3_PATH");
System.getenv("MP4_PATH");
```

Ensures the paths are correctly passed to your app.

---

## 9. Clean and Rebuild Project

```cmd
mvn clean package
```

- Cleans old build artifacts and packages the app into a JAR.
- Useful before adding new dependencies or plugins.

---

## 10. Common Maven Help Commands

### View Maven version:
```cmd
mvn -v
```

### Validate POM structure:
```cmd
mvn validate
```

### Build with debug output:
```cmd
mvn clean compile -X
```

### Show dependency tree:
```cmd
mvn dependency:tree
```

---

---
## Add -quiet flag to suppress Maven logs:

```cmd
mvn clean javafx:run -q
```
- Reduces log output to only essential information.
- Useful for cleaner output when running the app.

---

## ✅ Summary

Key terminal commands:

| Step | Command |
|------|---------|
| Generate project | `mvn archetype:generate` |
| Navigate | `cd my-first-app` |
| Compile | `mvn clean compile` |
| Run Java class | `mvn exec:java` |
| Set Windows env var (current session) | `set MP3_PATH=D:\media\song.mp3` |
| Set Windows env var (persistent) | `setx MP3_PATH=D:\media\song.mp3` |
| Run JavaFX with env vars | `mvn clean javafx:run` |
| Clean & package | `mvn clean package` |
| Validate POM | `mvn validate` |
| Show dependencies | `mvn dependency:tree` |

---
Additional Information:
Visit [Maven Commands Reference](https://maven.apache.org/ref/current/maven-embedder/cli.html) for more commands and options.
