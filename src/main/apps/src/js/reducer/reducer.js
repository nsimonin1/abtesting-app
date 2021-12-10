import { LOAD_CARS, NEW_CAR } from '../store/action-type';

const initialState = {
  cars: [],
}

const reducer = (state = initialState, action) => {
 switch(action.type) {
   case NEW_CAR: 
   const car = action.newCar;
    return {
      ...state,
      cars: state.cars.concat(car),
    }
  case LOAD_CARS:
    return {
      ...state,
      cars: action.cars,
    }
  default:
    return state
 }
}

export default reducer
