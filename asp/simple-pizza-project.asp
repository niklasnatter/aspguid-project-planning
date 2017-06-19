available_time_unit(days). available_time_unit(hours). available_time_unit(minutes). available_time_unit(seconds). available_time_unit(weeks). 
depends_on(bake_pizza,prepare_dough). depends_on(bake_pizza,prepare_toppings). depends_on(serve_pizza,bake_pizza). 
max_employee_count(2). 
max_project_duration(100). 
possible_start_time(0). 
task(bake_pizza,0,10). task(prepare_dough,1,10). task(prepare_toppings,1,10). task(serve_pizza,2,5). 
time_unit(minutes). 
