import React from 'react'
import ReactDOM  from "react-dom" 
import RouteComponent from './js/components/RouteComponent'
import { applyMiddleware, createStore} from 'redux'
import {Provider } from 'react-redux'
import thunk from 'redux-thunk'

import reducer from './js/reducer/reducer'

const wrapper = document.getElementById("container")

const store = createStore(reducer, applyMiddleware(thunk),
    window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__())

wrapper? ReactDOM.render(
    <Provider store={store}>
        <RouteComponent />
    </Provider>
    ,
    wrapper): false


