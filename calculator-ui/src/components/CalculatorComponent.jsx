import { useState } from 'react';
import UserHistoryComponent from './UserHistoryComponent';
import { Form, Button } from "react-bootstrap";
import { FcCalculator } from "react-icons/fc";


export default function CalculatorComponent() {

    const [mathematicalExpression, setMathematicalExpression] = useState('');

    const [errors, setErrors] = useState({});

    const onGenerateMathExpHandler = (event) => {
        event.preventDefault();
        setMathematicalExpression("1+1+2+3+4+5");
    };

    const onCalculateHandler = (event) => {
        event.preventDefault();
        const formErrors = validate();
        if (Object.keys(formErrors).length > 0) {
            setErrors(formErrors);
        } else {
            setErrors({});
            console.log('Calculate -> ', mathematicalExpression);
        }
    };

    const validate = () => {
        
        const newErrors = {};
        
        if (!mathematicalExpression) {
            newErrors.mathematicalExpression = 'Mathematical expression is required';
        }

        return newErrors;

    };

    return (
        <div className="container">
            <div className="row">
                <div className="col-5">
                    <Form className="mt-5" onSubmit={onGenerateMathExpHandler}>
                        <Button variant="primary" type="submit">Generate Mathematical Expression</Button>
                    </Form> 
                    <Form className='mt-4' onSubmit={onCalculateHandler}>
                        <Form.Group className="mb-3" controlId="formBasicMathExpression">
                            <Form.Control
                                type="text"
                                placeholder="Enter a valid mathematical expression"
                                value={mathematicalExpression}
                                onChange={(e) => setMathematicalExpression(e.target.value)}
                                isInvalid={!!errors.mathematicalExpression}
                            />
                            <Form.Control.Feedback type="invalid">
                                {errors.mathematicalExpression}
                            </Form.Control.Feedback>
                        </Form.Group>
                        <Button variant="primary" type="submit">Calculate</Button>
                    </Form>
                    <h5 className='mt-4'>Operation Result</h5>
                    <input type='text' disabled className='result-input'></input>
                    <div className='text-center'>
                        <FcCalculator size={300}/>
                    </div>
                </div>
                <div className="col-7 text-center">
                    <UserHistoryComponent />
                </div>
            </div>
        </div>
    );
}