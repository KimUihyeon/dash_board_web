import Axios from "axios";
import { citys } from "../util/City.js"
import { weatherService as WeatherService} from "./weather/WeatherService";
import { todoService as TodoService } from './todo/TodoService'; 
import { todoCategoryService as TodoCategoryService } from './todo/TodoCategoryService'
/**
 * 
 */


export const weatherService = WeatherService;
export const todoService = TodoService;
export const todoCategoryService = TodoCategoryService;