import Axios from "axios";

const COURSE_API_URL = 'http://localhost:8080';
const INSTRUCTOR_API_URL = `${COURSE_API_URL}/api`;


export function updateCar(code, car) {
    return function (dispatch) {
        return Axios.patch(`${INSTRUCTOR_API_URL}/cars/${code}`, car)
            .then(json => dispatch({type: "CAR_UPDATED_SUCCESSFULL", payload: json.data}));
    };    
}

export function getOneCar(code) {
    return function (dispatch) {
        return Axios.get(`${INSTRUCTOR_API_URL}/cars/${code}`)
            .then(json => dispatch({type: "LOADED_ONE_CAR", payload: json.data}));
    }
}

export function getCars() {
    return function(dispatch) {
    return Axios.get(`${INSTRUCTOR_API_URL}/cars`)
        .then(json => dispatch({ type: "LOADED_CARS", payload: json.data }));
    };
}
