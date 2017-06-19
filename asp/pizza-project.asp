available_time_unit(days). available_time_unit(hours). available_time_unit(minutes). available_time_unit(months). available_time_unit(seconds). available_time_unit(weeks). 
depends_on(bake_pizza,prepare_dough). depends_on(bake_pizza,prepare_toppings). depends_on(prepare_dough,go_shopping). depends_on(prepare_toppings,go_shopping). depends_on(serve_pizza,bake_pizza). depends_on(serve_pizza,cover_table). 
max_employee_count(2). 
max_project_duration(100). 
possible_start_time(0). 
task(bake_pizza,0,10). task(cover_table,1,10). task(go_shopping,1,15). task(prepare_dough,1,10). task(prepare_toppings,2,5). task(serve_pizza,1,5). 
time_unit(minutes). 
