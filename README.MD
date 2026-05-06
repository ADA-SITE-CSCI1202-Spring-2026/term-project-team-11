# Space Colony Management System


## 🚀 About the Project

The **Space Colony Management System** is a simulation framework that allows users to monitor and process different colony-related tasks including engineering, research, and life support. The application features a graphical user interface (GUI) to provide real-time feedback on the colony's state and resource management.

The project is structured using an MVC (Model-View-Controller) architecture to separate the simulation logic from the user interface.

---

## 🛠 Key Features

- **Task Management**: Handles specialized tasks such as `EngineeringTask`, `LifeSupportTask`, and `ResearchTask`.
- **Service Modules**: Includes dedicated processors like the `EngineeringBay` and `MedicalWard` to handle colony needs.
- **Dynamic UI**: A JavaFX-based dashboard (`DashboardView.fxml`) for visual interaction.
- **Persistence**: A `SaveLoadManager` to preserve and retrieve the colony's state.
- **Automated Generation**: A `TaskGenerator` service to simulate ongoing colony activity.

---

## 📂 Repository Structure

The project is organized into the following directory structure:

| Directory / File | Description |
| :-- | :-- |
| `src/app/` | Contains `MainApp.java`, the entry point for the application. |
| `src/model/` | Core data structures and task definitions (e.g., `ColonyTask`, `BaseState`). |
| `src/service/` | Business logic, task processors, and the `SaveLoadManager`. |
| `src/ui/` | JavaFX FXML files, controllers, and visual assets. |
| `init.txt` | Configuration file used for initializing the simulation state. |

---

## 📥 How to Download

To get a local copy of this repository, you can use the following command:
```
git clone https://github.com/ADA-SITE-CSCI1202-Spring-2026/term-project-team-11.git
```


## ⚙️ Prerequisites

Before compiling and running the application, ensure you have the following installed:

- **Java Development Kit (JDK):** Version 11 or higher
- **JavaFX SDK:** Download the JavaFX SDK that matches your operating system from Gluon HQ and extract it to a known location

## 🖥️ Compilation and Execution (Command Line)

Because this project relies on JavaFX, you must explicitly tell the Java compiler and runtime where the JavaFX library is located using the `--module-path` and `--add-modules` flags.

### 1. Set your JavaFX Path Variable

To make the commands easier, set an environment variable pointing to the `lib` folder inside your extracted JavaFX SDK.

**Windows (Command Prompt)**
```bash
set PATH_TO_FX="C:\path\to\javafx-sdk\lib"
```

**Mac/Linux**
```bash
export PATH_TO_FX="/path/to/javafx-sdk/lib"
```

### 2. Compile the Project

Navigate to the root directory of the project (where the `src` folder is located) and compile the Java files into a new `bin` directory.

**Windows**
```bash
dir /s /B src\*.java > sources.txt
javac --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml -d bin @sources.txt
```

**Mac/Linux**
```bash
find src -name "*.java" > sources.txt
javac --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml -d bin @sources.txt
```
### 3. Sync Resources (Crucial Step)
JavaFX requires FXML and CSS files to be present in the bin folder to load correctly.
```
:: Create the UI directory in bin
mkdir bin\ui 2>nul

:: Copy all UI assets (FXML, CSS, Images)
xcopy /s /y src\ui\* bin\ui\

:: Copy the initialization data
copy /y init.txt bin\
```
### 4. Run the Application

Once compiled, run `MainApp.java`, which acts as the entry point for the application.

**Windows**
```bash
java --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml -cp bin app.MainApp
```

**Mac/Linux**
```bash
java --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml -cp bin app.MainApp
```


## 📌 Notes

- Ensure your JavaFX version matches your JDK.
- Make sure all required files are correctly copied into the `bin` directory.
- If the UI does not load, verify your module path and file locations.
