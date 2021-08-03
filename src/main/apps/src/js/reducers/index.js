
const initialState = {
    cars: [],
    currentCar: null
};

function rootReducer(state = initialState, action) {
    if (action.type === "LOADED_CARS") {
        return Object.assign({}, state, {
          cars: state.cars.concat(action.payload)
        });
    }
    if (action.type === "LOADED_ONE_CAR") {
        return Object.assign({}, state, {
            currentCar: action.payload
        });
    }
    if(action.type === "CAR_UPDATED_SUCCESSFULL") {
        return Object.assign({}, state, {
            currentCar: action.payload
        });
    }
    return state;
}

export default rootReducer;