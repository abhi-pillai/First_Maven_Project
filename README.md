# My First Maven Project

This is my **first Maven project** built using Java and JavaFX. The project demonstrates a simple application that allows the user to play either an MP3 audio file or an MP4 video file. It also explores common issues when working with environment variables, system properties, Maven, and JavaFX.

---

## Project Overview

- **Language:** Java 17  
- **Build Tool:** Maven  
- **Libraries Used:**  
  - JavaFX Controls (`javafx-controls`)  
  - JavaFX Media (`javafx-media`)  
  - JUnit 5 (for testing)  

The application prompts the user to choose between MP3 and MP4 playback. MP3 audio plays without a window, while MP4 video is displayed in a JavaFX window with proper scaling.

---

## Features

1. Play an MP3 file (audio only).  
2. Play an MP4 file (video + audio) with scaling to fit the window.  
3. Read file paths from **environment variables** (`MP3_PATH` and `MP4_PATH`) to avoid hardcoding.  
4. Handles invalid input and missing paths gracefully.  

---

## Setup Instructions

### 1. Install Prerequisites

- Java 17 or later  
- Maven 3.x  
- JavaFX SDK (if not using Maven Central dependencies)  

### 2. Clone / Create Project

```bash
git clone <your-repo-url>
cd First_Maven_Project/my-first-app
```

### 3. Set Environment Variables

The project reads media file paths from environment variables:

**On Windows (Command Prompt):**

```bash
set MP3_PATH=D:\media\song.mp3
set MP4_PATH=D:\media\video.mp4
mvn clean javafx:run
```

**On Windows (PowerShell):**

```powershell
$env:MP3_PATH="D:\media\song.mp3"
$env:MP4_PATH="D:\media\video.mp4"
mvn clean javafx:run
```

**On Linux/Mac:**

```bash
export MP3_PATH="/path/to/song.mp3"
export MP4_PATH="/path/to/video.mp4"
mvn clean javafx:run
```

⚠️ **Important Notes:**

- Use `set` (not `setx`) on Windows to set variables for the current session.
- `setx` sets persistent variables but doesn't update the current terminal session.
- Environment variables must be set in the same terminal session before running Maven.

---

## Common Issues and Solutions

### Issue 1: "Environment variable not set!"

**Cause:**

- The code uses `System.getenv("MP3_PATH")` and `System.getenv("MP4_PATH")` to read environment variables.
- Windows `setx` only updates persistent environment variables and doesn't affect the current terminal session.
- You need to restart the terminal or use `set` instead of `setx` for immediate effect.

**Solution:**

In the same terminal window where you'll run Maven, set the variables using:

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

The code retrieves these values with:

```java
String path = System.getenv("MP3_PATH");
// or
String path = System.getenv("MP4_PATH");
```

### Issue 2: MP4 video appears zoomed in

**Cause:**

- `MediaView` by default displays the video at its native resolution.
- If the `Scene` or `Stage` size does not match the video, it can appear zoomed in or cropped.

**Solution:**

Use `fitWidth`, `fitHeight`, and `preserveRatio`:

```java
MediaView mediaView = new MediaView(mediaPlayer);
mediaView.setFitWidth(800);
mediaView.setFitHeight(600);
mediaView.setPreserveRatio(true);
```

Optional: Bind to stage size for responsive resizing:

```java
mediaView.fitWidthProperty().bind(stage.widthProperty());
mediaView.fitHeightProperty().bind(stage.heightProperty());
mediaView.setPreserveRatio(true);
```

---

## How to Run

1. Make sure your MP3 and MP4 files exist.

2. Set environment variables in your terminal:

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

3. Run the Maven command:

```bash
mvn clean javafx:run
```

4. When prompted:

```
Choose what to play:
1 - MP3
2 - MP4
```

- Enter `1` for MP3 audio.
- Enter `2` for MP4 video (video will appear in a window with correct scaling).

---

## Lessons Learned

- Windows `setx` sets persistent variables but doesn't update the current terminal session; use `set` for immediate effect.
- Environment variables must be set in the same terminal session before running Maven.
- JavaFX `MediaView` requires explicit scaling (using `setFitWidth`, `setFitHeight`, and `setPreserveRatio`) to display video properly.
- `System.getenv()` reads environment variables from the operating system.

---

## Project Structure

```
my-first-app/
├─ src/main/java/com/example/App.java
├─ pom.xml
├─ README.md
└─ media/          # place your MP3/MP4 files here
```