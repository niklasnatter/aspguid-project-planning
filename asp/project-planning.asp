%*::
{
  "program_information": {
    "name": "Project Planning",
    "description": "Showcase application for the aspguid language extension and the aspguid compiler. Computes a project plan for a given project instance according to the project planning problem.",
    "author": "Niklas Natter",
    "version": "0.1"
  },
  "program_input": {
    "@entity_input:task": {
      "title": "Project Tasks",
      "description": "Tasks of the project with their duration and the number of occupied employees.",
      "gui_representation":"::task_name takes ::duration time units and occupies ::employee_count employee(s)",
      "input_count_min": 1,
      "@input_attribute:task_name": {},
      "@input_attribute:employee_count": {
        "name": "Y",
        "value_source": "$#int"
      },
      "@input_attribute:duration": {
        "name": "X",
        "value_source": "$#int"
      }
    },
    "@entity_input:depends_on": {
      "title": "Task Dependencies",
      "description": "Dependencies between the tasks of the project.",
      "gui_representation": "::task1 depends on ::task2",
      "@input_attribute:task1": {
        "description": "Task which depends on task2",
        "value_source": "$task[0]"
      },
      "@input_attribute:task2": {
        "description": "Task on which task1 depends",
        "value_source": "$task[0]"
      }
    },
    "@value_input:project_management": {
      "title": "Project Management",
      "gui_representation": "Project time unit: ::time_unit. Maximum project duration: ::max_project_duration. Number of employees: ::max_employee_count.",
      "@input_value:time_unit": {
        "name": "time unit",
        "default_value": "days",
        "value_source": "$available_time_unit[0]"
      },
      "@input_value:max_project_duration": {
        "name": "number",
        "value_source": "$#int"
      },
      "@input_value:max_employee_count": {
        "name": "number",
        "value_source": "$#int"
      }
    }
  },
  "program_output": {
    "@entity_output:planned_task": {
      "title": "Project Plan",
      "description": "Project plan for the given project instance. The project plan contains all tasks of the project along with their planned starting time and ending time.",
      "gui_representation": "[::start - ::end]: ::task_name",
      "@output_attribute:task_name": {},
      "@output_attribute:start": {},
      "@output_attribute:end": {}
    },
    "@value_output:time_information": {
      "gui_representation": "The total project duration is ::project_duration ::time_unit1. All employees are working on a task simultaneously for a total of ::full_utilization_duration ::time_unit2.",
      "@output_value:project_duration": {},
      "@output_value:full_utilization_duration": {},
      "@output_value:time_unit1": {
        "atom_representation": "time_unit(::time_unit1)"
      },
      "@output_value:time_unit2": {
        "atom_representation": "time_unit(::time_unit2)"
      }
    },
    "@value_output:utilization_warning": {
      "title": "Warning!",
      "gui_representation": "At least one employee is not working on a task for over 75% of the project duration.",
      "output_condition": "?bad_utilization"
    }
  }
}
::*%

#maxint=200.

% define available time units for GUI
available_time_unit(seconds).
available_time_unit(minutes).
available_time_unit(hours).
available_time_unit(days).
available_time_unit(weeks).
available_time_unit(months).

% zero is a possible start time for a task, also every end of a planned task is a possible start time
possible_start_time(0).
possible_start_time(Time) :- planned_task(_,_,Time).

% plan every task
planned_task(Name,Start,End) v -planned_task(Name,Start,End) :- task(Name,_,Duration), possible_start_time(Start), End = Start + Duration.

% each task must be planned
task_is_assigned(Name) :- task(Name,_,_), planned_task(Name,_,_).
:- task(Name,_,_), not task_is_assigned(Name).

% a task must not be assigned to more than one start time
:- task(Name,_,_), planned_task(Name,Start1,_), planned_task(Name,Start2,_), Start1 <> Start2.

% a task which depend on an other tasks must not start, before the other tasks is completed
:- depends_on(Task1,Task2), planned_task(Task1,Start,_), planned_task(Task2,_,End), End > Start.

% the planned project duration must not exceed the maximum duration of the project
project_duration(Duration) :- #max{End : planned_task(_,_,End)} = Duration, #int(Duration).
:- project_duration(Duration), max_project_duration(MaxDuration), Duration > MaxDuration.

% time points during the project
time_point(X) :- project_duration(Duration), #int(X), X >= 0, X < Duration.

% the number of employees assigned to a task on a specific time point must not exceed the number of employees of the project
current_task(Time,Name) :- time_point(Time), planned_task(Name,Start,End), Start <= Time, End > Time.
current_employee_count(Time,Count) :- time_point(Time), #sum{EmployeeCount,Name : task(Name,EmployeeCount,_), current_task(Time,Name)} = Count, #int(Count).
:- current_employee_count(_,EmployeeCount), max_employee_count(MaxEmployeeCount), EmployeeCount > MaxEmployeeCount.

% calculate duration of full staff utilization throughout the project
full_utilization_duration(Duration) :- max_employee_count(MaxEmployeeCount), #count{T : current_employee_count(T,C), C = MaxEmployeeCount} = Duration, #int(Duration).
partial_utilization_duration(Duration) :- full_utilization_duration(FullUtilization), project_duration(ProjectDuration), Duration = ProjectDuration - FullUtilization.

% tag plan with bad_utilization, if the ful staff utilization duration is below 25% of the project duration
bad_utilization :- full_utilization_duration(FullUtilization), partial_utilization_duration(PartialUtilization), TempFullUtilization = FullUtilization * 3, TempFullUtilization < PartialUtilization.