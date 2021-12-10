import { PropTypes } from 'prop-types'
import React, { useState, useEffect } from 'react'

import CarDataService from '../../services/CarDataService'
import ListCars from '.'
import { LOAD_CARS } from '../../store/action-type'
import { connect } from 'react-redux'

const ListCarsComponent = ({ cars, loadCars}) => {

	const [message, setMessage] = useState(null)
    
	useEffect(() => {
		refreshCourses()
	}, [])


	const refreshCourses = () => {
		CarDataService.retrieveAllCars()
			.then(response => {   
				loadCars(response.data)
			}
		)
	}

    
	return (  
		<ListCars cars={cars} /> 
	)
    
}

const mapStateToProps = state => {	
	return {
		cars: state.cars,
	}
}

const mapDispatchToProps = dispatch => {
	return {
		loadCars: cars => 
			dispatch({
			type: LOAD_CARS,
			cars,
		})
	}
}

export default connect(mapStateToProps, mapDispatchToProps)(ListCarsComponent)
