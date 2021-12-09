const initialState = {
  cars: [],
}

const reducer = (state = initialState, action) => {
 switch(action.type) {
   case 'NEW': 
   const car = action.newCar;
    return {
      ...state,
      cars: state.cars.concat(car),
    }
  default:
    return state
 }
}

export default reducer
