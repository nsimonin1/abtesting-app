import {Field, reduxFrom} from "redux-form";

const mapStateToProps = (state) => {
    return {
        initialValues: {
            code: state.currentCar.code
        }
    }
}

const CarForm = props => {
    const {handleSubmit, code} = props
    return (
        <Form onSubmit={handleSubmit}>
            <div className="row">
                <div className="col-6">
                    <div className="form-group">
                        <label >Code</label>
                        <Field name="code" component="input" type="text"/>
                    </div>
                </div>
            </div>
            <button type="submit">Submit</button>
        </Form>
    )
}

export default connect(mapStateToProps) (reduxFrom({
    form: 'carForm', enableReinitialize: true
}))(CarForm);
