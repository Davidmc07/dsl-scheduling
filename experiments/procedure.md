# Usability Testing Procedure 
1. A tutor meets with each participant individually in a room equipped with a computer containing the following software: 
* The DSL IDE, with a pre-created project containing: 
  * A specific file for each task where the program will be written.
  * A CSV file where vehicles will be added manually. 
* A web browser logged into the following CMMS: 
  * UpKeep
  * MaintainX
  * IBM Maximo
2. The tutor introduces the maintenance scheduling problem, the objectives of the different tools to be used, and the test procedure. See Appendix 1.

3. The tutor provides a general explanation of the main features of each tool before its corresponding test, demonstrating how to use the functionalities required for the proposed tasks. The time spent explaining each tool will be recorded. See Appendix 2.
 
4. The participant performs all tasks using the explained tool. For each task, the objective and the relevant values to be used are explained. The maximum time for each task is 10 minutes. The tutor records the time taken by the participant and whether they were able to complete the task. See Appendix 3.

5. After completing all tasks, participants fill out a questionnaire regarding the usability of the tools.

During each task, participants will have a printed summary of the tool explanation. During Task 1 for each tool, the tutor may answer questions and assist the participant.

---
# Appendix 1

The objective of this test is to plan periodic maintenance for a fleet of vehicles using different tools.

Currently, many companies use vehicle fleets in sectors such as tourism or commerce. One of the key tasks in fleet management is planning maintenance for each vehicle. This planning can significantly impact both total maintenance costs and vehicle downtime.

Typically, different types of maintenance, each with its own duration and interval (time between maintenance of the same type), must be carried out in specific facilities for each vehicle.

Most programs used for maintenance planning are Computerized Maintenance Management Systems (CMMS). These are applications designed to manage company assets and tend to be complex due to the wide range of tasks they cover.

As an alternative to CMMS, we propose a domain-specific language (DSL) that generates maintenance planning automatically from a structured textual specification of maintenance tasks and facilities.

This study evaluates both the DSL and CMMS tools to determine whether the process has been simplified.

During the test, each tool will be explained and a training task will be performed. Then, participants will complete three planning tasks with a 10-minute limit each. Finally, participants will complete a questionnaire evaluating each tool.

---

# Appendix 2

## UpKeep

**Create facilities:**
- Section: "Locations"
- Only the title needs to be filled in

**Create vehicles:**
- Section: "Assets"
- Fill in name and assign location

**Plan maintenance:**
- Section: "Preventive Maintenance"
- At least one PM per maintenance type is required
- Calculate repetition interval based on daily usage hours
- Fill in:
  - Name
  - Title
  - Repetition interval
  - Assets
  - Estimated duration (hours)

**View maintenance:**
- Select maintenance
- Switch to "Calendar" tab

---

## MaintainX

**Create facilities:**
- Section: "Locations"
- Only the name is required

**Create vehicles:**
- Section: "Assets"
- Fill in name and assign location

**Plan maintenance:**
- Section: "Work Order Library"
- At least one template per maintenance type is required
- Calculate approximate repetition interval based on daily usage
- Fill in:
  - Name
  - Repetition interval (preventive maintenance)
  - Estimated duration
- Use template to create work orders for each vehicle

**View maintenance:**
- Section: "Work Orders"
- Switch to "Calendar View"

---

## IBM Maximo

**Create facilities:**
- Section: "Assets → Locations"
- Fill in name and type (e.g., operating)
- Change status to "Active"

**Create vehicles:**
- Section: "Assets → Assets"
- Fill in name and location
- Change status to "Active"

**Plan maintenance:**
- Section: "Preventive Maintenance"
- Calculate approximate repetition interval
- Fill in:
  - Name
  - Interval/frequency
  - Asset
- Change status to "Active" (ignore errors)
- Generate work orders (ignore errors)

**View maintenance:**
- Section: "Work Orders"
- No calendar view available

---

## DSL

**Create vehicles:**
- Add a line in "data.csv" for each vehicle:
  - `[NAME], [DAILY USAGE HOURS]`
- Save file

**Plan maintenance:**
- Write program (CTRL + SPACE for templates)
- In `[Options]`, specify CSV input file
- Define installations:
  - ID, name, capacity
- Define maintenance:
  - ID, name, interval, duration, installation IDs
- Save (CTRL + S) and execute

**View maintenance:**
- Refresh project (F5)
- Open "output_report.html"

**Syntax:**

```
[Options]
Input: "file.csv"

[Installations]
Installation i1
  Name: "Installation Name"
  Capacity: [Capacity]

[Maintenances]
Maintenance m1
  Name: "Maintenance Name"
  Interval: [Interval]
  Periods: [Duration]
  Installations: i1
```

---

# Appendix 3

## Task 1

Plan two types of maintenance for 2 vehicles:

**Facilities:**
- Installation1 (for all)
  - Name: "Installation 1"
  - DSL capacity: 10

**Vehicles:**
- V1, V2
- Daily usage: 5 hours

**Maintenance types:**

- M1:
  - Interval: every 100 hours
  - Duration: 3 days (DSL) / 24 hours (UpKeep)

- M2:
  - Interval: every 50 hours
  - Duration: 4 days (DSL) / 32 hours (UpKeep)

---

## Task 2

Plan one maintenance type for 2 vehicles with different usage:

**Facilities:**
- Installation1

**Vehicles:**
- V1: 0.5 hours/day
- V2: 2 hours/day

**Maintenance:**

- M1:
  - Interval: every 20 hours
  - Duration: 5 days (DSL) / 40 hours (UpKeep)

---

## Task 3

Plan two maintenance types for 5 vehicles:

**Facilities:**
- Installation1

**Vehicles:**
- V1 to V5
- Daily usage: 1 hour

**Maintenance:**

- M1:
  - Interval: every 20 hours
  - Duration: 5 days (DSL) / 40 hours (UpKeep/MaintainX)

- M2:
  - Interval: every 30 hours
  - Duration: 2 days (DSL) / 16 hours (UpKeep/MaintainX)
