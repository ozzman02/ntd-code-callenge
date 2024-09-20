import { useState, useEffect } from 'react';
import UserHistoryComponent from './UserHistoryComponent';
import { Form, Button } from "react-bootstrap";
import { FcCalculator } from "react-icons/fc";
import useAuthorizationContext from '../hooks/UseAuthorizationContext';
import { generateMathematicalExpression, calculateResult, getUserRecords } from '../services/CalculatorService';


export default function CalculatorComponent() {

    const { getAuthorizationHeader, getUser } = useAuthorizationContext();

    const [mathematicalExpression, setMathematicalExpression] = useState('');

    const [operationResult, setOperationResult] = useState('');

    const [errors, setErrors] = useState({});

    const [userRecords, setUserRecords] = useState([]);

    const [pageNumber, setPageNumber] = useState(0);

    const [totalPages, setTotalPages] = useState(0);

    const [newUserRecord, setNewUserRecord] = useState(false);

    const { userId } = getUser();

    const authorizationHeader = getAuthorizationHeader();    

    useEffect(() => {
        fetchUserRecords();
    }, [pageNumber]);

    useEffect(() => {
        if (newUserRecord === true) {
            fetchUserRecords();
        }
    }, [newUserRecord]);

    const pageNavigationHandler = (event) => {
        console.log(event);
        const selectedPage = event.selected;
        setPageNumber(selectedPage);
    };

    const fetchUserRecords = async () => {
        try {
            const response = await getUserRecords(authorizationHeader, userId, pageNumber);
            setUserRecords(response.data.content);
            setTotalPages(response.data.totalPages);
            setPageNumber(response.data.pageable.pageNumber);
        } catch (error) {
            console.log(error);
        }
    };

    const onGenerateMathExpHandler = async (event) => {
        event.preventDefault();
        setNewUserRecord(false);
        try {
            const response = await generateMathematicalExpression(authorizationHeader);
            setMathematicalExpression(response.data);
        } catch (error) {
            console.log(error);
        }        
    };

    const onCalculateHandler = async (event) => {
        event.preventDefault();
        const formErrors = validate();
        if (Object.keys(formErrors).length > 0) {
            setErrors(formErrors);
        } else {
            setErrors({});
            try {
                const response = await calculateResult(authorizationHeader, { mathematicalExpression });
                setOperationResult(response.data.operationResponse);
                setMathematicalExpression('');
                setErrors({});
                setNewUserRecord(true);
            } catch (error) {
                console.log(error);
            }
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
                                className="text-end"
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
                    <input type='text' disabled className='result-input' value={operationResult}></input>
                    <div className='text-center'>
                        <FcCalculator size={300}/>
                    </div>
                </div>
                <div className="col-7 text-center">
                    <UserHistoryComponent 
                        userRecords={userRecords} 
                        totalPages={totalPages} 
                        pageNavigationHandler={pageNavigationHandler} 
                    />
                </div>
            </div>
        </div>
    );
}