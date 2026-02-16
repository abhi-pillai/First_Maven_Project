# Help / Troubleshooting Guide for My First Maven Project

This file documents the errors and issues encountered while building and running the **My First Maven Project**, along with explanations and alternative solutions.

---

## 1. POM Parsing Errors

### Error Example

```
Non-parseable POM ... pom.xml: Duplicated tag: 'build'
Unrecognised tag: 'dependency'
```

### Cause

- XML in `pom.xml` was **malformed**:
  - Duplicated `<build>` tags.
  - `<dependency>` placed incorrectly or outside `<dependencies>` block.
  - Improper indentation sometimes hides structural issues (though indentation itself is not an error for Maven).

### Solution

- Ensure a **single `<build>` block** exists.  
- Place all `<dependency>` entries inside `<dependencies>`:

```xml
<dependencies>
  <dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-media</artifactId>
    <version>21</version>
  </dependency>
</dependencies>
```

- Validate POM with `mvn validate` before running.

### Alternative

Use a working Maven archetype (e.g., `maven-archetype-quickstart`) to generate a valid initial POM.

---

## 2. Maven Exec Plugin Error

### Error Example

```
The parameters 'mainClass' for goal org.codehaus.mojo:exec-maven-plugin:3.6.3:java are missing or invalid
```

### Cause

- `exec-maven-plugin` requires `mainClass` to be defined in the plugin configuration.
- Maven cannot automatically detect which class to run if it's missing.

### Solution

Add `mainClass` explicitly in `pom.xml`:

```xml
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>exec-maven-plugin</artifactId>
    <version>3.6.3</version>
    <configuration>
        <mainClass>com.example.App</mainClass>
    </configuration>
</plugin>
```

---

## 3. Environment Variable Not Set

### Error Example

```
Environment variable not set!
```

### Cause

- `System.getenv("MP3_PATH")` returned `null`.
- Common reasons:
  - Used `setx` in Windows but did not restart the terminal.
  - Quotes included in `setx` command caused literal quotes in the value.
  - Environment variables not set in the current terminal session.

### Solution

Set environment variables in the current terminal session:

**Windows (Command Prompt):**
```bash
set MP3_PATH=D:\media\song.mp3
set MP4_PATH=D:\media\video.mp4
```

**Windows (PowerShell):**
```powershell
$env:MP3_PATH="D:\media\song.mp3"
$env:MP4_PATH="D:\media\video.mp4"
```

**Linux/Mac:**
```bash
export MP3_PATH="/path/to/song.mp3"
export MP4_PATH="/path/to/video.mp4"
```

The code reads these values with:

```java
String path = System.getenv("MP3_PATH");
```

### Alternative Solutions

1. **Use `FileChooser` in JavaFX** for runtime file selection.

2. **Use System Properties** instead of environment variables:
   - Modify the code to use:
     ```java
     String path = System.getProperty("MP3_PATH");
     ```
   - Configure JavaFX Maven plugin to pass JVM options:
     ```xml
     <plugin>
         <groupId>org.openjfx</groupId>
         <artifactId>javafx-maven-plugin</artifactId>
         <version>0.0.8</version>
         <configuration>
             <mainClass>com.example.App</mainClass>
             <options>
                 <option>-DMP3_PATH=D:\media\song.mp3</option>
                 <option>-DMP4_PATH=D:\media\video.mp4</option>
             </options>
         </configuration>
     </plugin>
     ```

---

## 4. MP4 Video Zoomed In

### Issue

Video plays but is cropped or zoomed, not properly fitted to the window.

### Cause

- `MediaView` defaults to native video resolution.
- `Scene` or `Stage` size differs from video size.
- `preserveRatio` not set, so scaling can distort or crop.

### Solution

```java
MediaView mediaView = new MediaView(mediaPlayer);
mediaView.setFitWidth(800);     // Scene width
mediaView.setFitHeight(600);    // Scene height
mediaView.setPreserveRatio(true);
```

**Optional:** Bind to `Stage` size for responsive resizing:

```java
mediaView.fitWidthProperty().bind(stage.widthProperty());
mediaView.fitHeightProperty().bind(stage.heightProperty());
mediaView.setPreserveRatio(true);
```

---

## 5. Hardcoding Works but Env Vars Fail

### Issue

Hardcoded paths work:

```java
path = "D:\\media\\song.mp3";
```

But environment variables don't.

### Cause

- Environment variables not visible to current terminal or Maven JVM.
- Environment variables not set in the same terminal session before running Maven.

### Solution

- Set environment variables using `set` (Windows) or `export` (Linux/Mac) in the **same terminal session** before running `mvn clean javafx:run`.
- Avoid relying on Windows `setx` without restarting the terminal.
- Avoid hardcoding paths in production code.

---

## 6. General Recommendations

- Always close and reopen terminals after `setx` on Windows, or use `set` for immediate effect in the current session.
- Validate POM structure with `mvn validate` to avoid Maven parsing errors.
- Use environment variables set in the current session, or consider runtime file selection for media paths.
- For MP4 video, always use a `MediaView` with `fitWidth`, `fitHeight`, and `preserveRatio`.

---

## Summary

This guide documents the key errors faced when learning Maven, JavaFX, and media playback:

| Error / Issue | Cause | Solution |
|--------------|-------|----------|
| Malformed POM | Duplicated tags, wrong placement | Fix XML structure, validate with Maven |
| `exec:java` missing `mainClass` | Plugin cannot detect main class | Add `<mainClass>` in `exec-maven-plugin` |
| Env var not set | Windows `setx` or not set in current session | Use `set`/`export` in current terminal session |
| MP4 zoomed | `MediaView` shows native resolution | Use `fitWidth`, `fitHeight`, `preserveRatio` |
| Hardcoding works, env var fails | Environment variables not visible to JVM | Set env vars in current session before running Maven |