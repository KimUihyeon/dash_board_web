import Axios from "axios";
import { citys } from "../util/City.js"
import { weatherService as WeatherService} from "./weather/WeatherService";
import { todoService as TodoService } from './todo/TodoService'; 
/**
 * 
 */


export const weatherService = WeatherService;
export const todoService = TodoService;