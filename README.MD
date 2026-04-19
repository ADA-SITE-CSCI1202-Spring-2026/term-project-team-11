# 🚀 The Ares Base: Survival Dashboard
**Team 11 - Mars Colony Management Simulation**

## 📌 Project Overview
The Ares Base is a real-time management simulation designed to test the survival of a Martian colony. As the Base Commander, players must manage critical resources while responding to a constant stream of emergencies. The simulation utilizes an automated engine to generate tasks that must be resolved using specialized base modules.

## 🛠 Technical Features
This project implements core Object-Oriented Programming (OOP) principles as required by the course syllabus:

* **Inheritance:** Utilizes an abstract `Task` base class in the `model` package with specialized subclasses (e.g., `FixTask`, `EmergencyTask`) to model different crisis types.
* **Polymorphism:** Implements a `TaskProcessor` interface in the `service` package, allowing diverse base modules to handle crises through a unified processing method.
* **Encapsulation:** Protects the colony's state through private fields and controlled public accessors (getters/setters) in the `BaseState` and `Task` classes.
* **Collections:** Manages the flow of emergencies using a `Queue` (FIFO) data structure for incoming tasks via the `TaskGenerator`.
* **Persistence:** Features a `SaveLoadManager` to preserve base progress and resource levels via Java File I/O.
* **GUI:** A futuristic JavaFX dashboard (MVC pattern) featuring real-time vitals, resource tracking, and event logging.

## 📂 Project Structure
Based on the architectural layout of the `movlan4` branch:

```text
AresBaseDashboard/
├── src/
│   ├── app/            # Application entry point (MainApp.java)
│   ├── model/          # Data structures (Task.java, FixTask.java, BaseState.java)
│   ├── service/        # Business logic (TaskGenerator.java, TaskProcessor.java)
│   └── ui/             # UI Logic (DashboardController.java)
├── README.md           # Project documentation
└── .gitignore          # Git exclusion rules