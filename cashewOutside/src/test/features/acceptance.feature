

Boiled Egg - Basic UI and loading existing data (data)
Given <executable_program>
When <client_clicks>
Then <executable_runs> with GUI interface with menu bar, toolbar, data loads and is accessible.

Given <new_data_file> File is in XML format and conforms to DTD (stock items, suppliers, menus, recipes for menu items)
When <user_adds>
Then <add_to_system> AND <user_views_table>

Given <new_data>
When <user_types> into field
Then <add_to_system> AND <user_views_table>

Given <data_table>
When <user_modifies_filters> GUI selection of filtering data
Then <view_desired_content> viewing selected data, or generating reports(?)

Future Expansion:
Given <other_file_types>
When <uploaded>
Then <add_to_repository> Future capacity to accept other file types

Onion Soup - Ready for business (prep)
Given <menu_screen>
When <user_selects> selects to view menu
Then <visualise_menu_options> GUI displays representation of menu items, with recipe, and ingredients for display

Given <system> ready to perform a function that requires listing recipe ingredients
When <chef_selects_meal>
Then <display_ingredients>

Given <stock_levels>
When <calculate_servings>
Then <display_quantities_available>

Given <dietary_requirements>
When <special_diet_selected> special dietary requirements are required
Then <display_special_meals> menu items with V, VG, GF, HL, Allergies etc.

Future Expansion:
Given <list_of _ingredients> in inventory
When <user_queries> what meals are possible with ingredients available
Then <display_meals> meals that can be prepared

Cheeseburger - Basic order operation (sales screen)
Given <customer_orders_item(s)>
When <user_selects_ordered_item(s)> user: sales operator
Then <quantity_of_ordered_item(s)> AND <availability_of_ingredients_to_make_ordered_item(s)> AND <orders_total_cost_displayed>

Given <customer_confirms_orders>
When <user_selects_”order is confirmed”>
Then <relevant_ingredient_quantities_updated>  AND <quantities_deducted_from_stock> AND <sales_details_recorded> details of sales: items ordered, date & time, payment type, etc

Given <customer_pays>
When <user_receives_money> AND <confirms_payment_in_system>
Then <order_details_and_money_recorded_in_database>

Ginger Crunch - (Quality of GUI)
Given <data_in_list>
When <when_data_is_clicked>
Then <details_available> eg: ingredients corresponding to the selected item

Given <items_in_list>
When <view_items_in_menu>
Then <items_sorted_in_appropriate_orders> eg: alphanumeric or by category

Given <customer_select_more_than_one_order>
When <customer_decide_on_a_primary_order>
Then <order_status_changed_to_primary>

Given <files_opened>
When <not_needed> OR <times_out>
Then <files_closed>


Gumbo - (Business Operation)
Given <cash_float> truck has a cash float with certain denominations
When <owner_opens>
Then <show_status_float>

Given <cash_float>
When <reconciliation>
Then <show_earnings> and <show_float_totat>

Given <sale>
When <customer_purchases> meal
Then <correct_change_given> AND <float_updated> and <kitchen_notified>

Given <customer_cancels>
When <refund_made>
Then <float_updated> AND <kitchen_notified> AND <ingredients_used>

Nicoise Salad - (Business Data Analysis)
Given <variation to costs>
When <compute_cost_of_item>
Then <update_menu>

Given <order_made> OR <stores removed>
When <low_stock_levels>
Then <send_warning>

Given <data> eg.sales data, stock
When <staff_selects_data_analysis>
Then <display_appropriate_data_analytics>

Given <sales_transaction>
When <data_logged>
Then <available_for_replay_and_analysis>

Coq an van - (future data loading operations)
Given <duplicate_data> data that is already in that data set
When <user_imports>
Then <check for duplicates> AND <user_alerted_to_conflict> AND <option_to_skip> OR <cancel_import>

Given <conflicting_data>
When <user_adds>
Then <display_option_menu> This will display options of how to deal with the conflict

Given <new_data> OR <update_required>
When <user_types> i.e. entered data manually
Then <add_to_system>

Salt-fried pork - (storage of media)
Given <data_stored>
When <user_restarts_app>
Then <user_retains_acess_to_data>

Given <open_data_file>
When <user_opens_another_file>
Then <display_offer_to_close_previous_file>

Given <data type or storage>
When <user queries data type>
Then <data_stored_type_returns> AND <data_input_types_returned>

Given <client_accessing_file> i.e. client is using recipe files
When <check_files_close>  system runs a check to close unused files
Then <unrelated_files_able_to_close_without_conflict> ability to close individual files (not one giant storage file for system)



Lasagna - (visualizations)
Given <sales_data>
When <data_processed>
Then <graph_representation_available> eg. Item popularity, profitability

Given <foodtruck_visits_multiple_locations>
When <user_selects_maps>
Then <displays_foodtrucks_route_and_locations>

Given <sales_data>
When <data_processed>
Then <visual_reports_available>

Given <foodtruck_is_booked_for_upcoming_events>
When <user_selects_calender>
Then <displays_upcoming_events_in_calender_format>

Boar Pate - Modelling data
Given <event>
When <persona_set>
Then <suggest_menu> OR <suggest_stock>

Given <customer_data>
When <purchase_made>
Then <estimated_times> for the business owner to model customer wait times

Given <queue>
When <greater_than1>
Then <show_queue_time>

Given <queue_time>
When <greaterThanX>
Then <suggest_options> more staff, more vehicles

Given <event>
When <model_optimal_stock>
Then <provide_optimal_levels> seasonal and persona dependant



Char Koay Teow - Advanced
Given <data>
When <when_user_clicks_”export”>
Then <data_exported_into_remote_database_through_network>

Given <number of servings displayed>
When <when_user_selects_one or more serving(s)>
Then <calorie_count_displayed per serving_and_accumulated>

Given <user_buys_items>
When <user_is_a_premium_member>
Then <points_are_earned> tailored to the amount of items purchased

Given <sufficient_points_earned>
When <user_choose_to_redeem_points>
Then <points_redeemed_for_discount_on_further_purchases>

Given <menu_and_ingredient_displayed_in_English> AND <currency_is_in_NZD>
When <user_chooses_to_view_in_foreign_language> AND <its_corresponding_currency>
Then <menu_and_ingredients_displayed_in_foreign_language> AND <currency_conversion_available>

Given <customer_has_physical_impairments>
When <user_selects_”assistive help”_button>
Then <application_accessible_to_physically_impaired_customer> eg: voice over

Given <supplier_location> AND <truck_location_known>
When  <user_select_”Calculate food miles”>
Then <food_miles_and_carbon_footprint_calculated> AND <data_displayed>

Given <weather_forecast > (Web API)
When <weather_forecast_used > (Web API)
Then <demand_of_sales_predicted>

Given <Web API_for_payment_by_credit_card>
When <customer_chooses_to_pay_by_credit_card>
Then <Web API_utilised_to_accept_payment>

Given <user_data>
When <user_selects_”export”>
Then <types_of_format_displayed_for_user_to choose>

Given <food_truck_business>
When <business_has_more_than_one_operator>
Then <shared_access_to_resources/database_implemented>

Further Expansion:
Given <stock_expiry>
When <stock_nears_expiry>
Then <alert_specials> AND <implement special pricing> i.e.  run a 2 for 1 hour,

Could use simulation, reporting, visualisation.How to cost them, impact of loss leaders.

