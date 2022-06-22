import { combineReducers } from "redux";
import bookReducer from "./bookReducer";
import errorReducer from "./errorReducer";
import messageReducer from "./messageReducer";
import orderReducer from "./orderReducer";
import personReducer from "./personReducer";
import securityReducer from "./securityReducer";

export default combineReducers({
  errors: errorReducer,
  person: personReducer,
  book: bookReducer,
  order: orderReducer,
  security: securityReducer,
  messages: messageReducer,
});
