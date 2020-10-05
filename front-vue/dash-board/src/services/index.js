import { weatherService as WeatherService} from "./weather/WeatherService";
import { todoService as TodoService } from './todo/TodoService'; 
import { todoCategoryService as TodoCategoryService } from './todo/TodoCategoryService'
import { accountService as AccountService } from './account/AccountService'
import { calendarService as CalendarService } from './calendar/CalendarService'
import { eventService as EventService } from './calendar/EventService'



export const weatherService = WeatherService;
export const todoService = TodoService;
export const todoCategoryService = TodoCategoryService;
export const accountService = AccountService;
export const calendarService = CalendarService;
export const eventService = EventService;

