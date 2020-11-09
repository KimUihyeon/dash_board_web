import { rest, date, data } from "../../util";
import { getBaseUrl, getCurrentLoginID, httpAuhorizationHeaderConfig } from "../common/ServiceHelper";

const base_url = getBaseUrl() + "/v1/calendar";

const getUserCalendar = (userId) => rest.authGet(`${base_url}/${userId}`, { userId: userId })

const addCalendar = (cal) => {
  let calendar = {
    ...cal,
    accountId: "admin@admin.com",
  };
  return rest.authPost(`${base_url}/`, calendar);
};

const deleteCalendar = (cal) => rest.authDelete(`${base_url}/${cal.id}`)

const updateCalendar = (cal) => rest.authPatch(`${base_url}/${cal.id}`, cal)

export const calendarService = {
  getUserCalendar,
  addCalendar,
  deleteCalendar,
  updateCalendar,
};
