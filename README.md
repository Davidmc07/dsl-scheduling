# Domain-Specific Language for maintenance scheduling in vehicle fleets

<img width="30%" alt="universidad-de-oviedo" src="https://github.com/user-attachments/assets/39ed4a2e-9860-40b7-81ec-490f61694612" />

<img width="42%" alt="image_gallery" src="https://github.com/user-attachments/assets/ad3c87ae-2163-4a2b-aa44-354d954b7c21" align="right"/>



## About

This work presents a novel Domain-Specific Language (DSL) for scheduling maintenances in vehicle fleets under several constraints. The DSL enables users without prior programming knowledge to generate optimized maintenance calendars from their vehicle data (at least ID and estimated usage per day) and a written specification of the maintenance types, installations, and configuration parameters, following a simple and straightforward syntax.

* Author: **David Martínez Castañón**
* Tutor: **PhD. Vicente García Díaz**
<br>
<img width="1920" height="1030" alt="ide" src="https://github.com/user-attachments/assets/c87b751a-8088-4ba6-ba83-19fd25b6f9a2" />

## Input file

To schedule your fleet's maintenances, the DSL needs to know the ID associated with each vehicle and their estimated usage per day. This data is passed as a CSV file in the `Input` parameter, under the `[Options]` section. Additional attributes can be used to specify the current state of the fleet.

| Attribute                 | Type     | Description                                                                                                                              | Required |
|---------------------------|----------|------------------------------------------------------------------------------------------------------------------------------------------|----------|
| `ID`                      | `string` | Unique identifier for the vehicle                                                                                                        | Yes       |
| `Usage per day`           | `float`  | Estimated usage per day                                                                                                                  | Yes       |
| `Active`                  | `boolean`| Whether the vehicle is active or under maintenance                                                                                       | No      |
| `Maint duration`          | `int`    | If not active, the duration of the current maintenance                                                                                   | No      |
| `Count days in maint`     | `int`    | If not active, the number of elapsed days under maintenance                                                                              | No      |
| `Total usage`             | `float`  | Total usage, including the amount since the last maintenance and from previous maintenance cycles                                        | No      |
| `Last [maint. name]`      | `float`  | The total usage at which the last maintenance of each type was performed. The maintenance name must match the one defined on the program | No      |

## Output

After executing the file, an `output` folder will be generated inside the project. In addition to the JSON files containing detailed data of the evolution of the schedule, the `report.html` file shows a Gantt diagram with all the maintenances and plots for the installations capacity and vehicle availability.

<img width="1920" height="924" alt="report_schedule" src="https://github.com/user-attachments/assets/6240faaa-dd5a-42a0-8571-87402e62c43b" />
<img width="49.5%" alt="report_availability" src="https://github.com/user-attachments/assets/151ab246-ae24-48a4-90e5-af9b30ec57ed" />
<img width="49.5%" alt="report_maintenances" src="https://github.com/user-attachments/assets/5ec9ac6c-f742-4a35-ad30-7d0873124c93" />



