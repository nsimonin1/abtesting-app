import Axios from "axios";

const COURSE_API_URL = 'http://localhost:8080';
const INSTRUCTOR_API_URL = `${COURSE_API_URL}/api`;

class CarDataService {
    
    retrieveAllCars() {
        return Axios.get(`${INSTRUCTOR_API_URL}/cars`);
    }

    retrieveOneCare(code) {
        return Axios.get(`${INSTRUCTOR_API_URL}/cars/${code}`);
    }

    updateCar(code, car) {
        return Axios.patch(`${INSTRUCTOR_API_URL}/cars/${code}`, car);
    }

    createcar(car) {
        return Axios.post(`${INSTRUCTOR_API_URL}/cars`, car)
    }
}

export default new CarDataService()
