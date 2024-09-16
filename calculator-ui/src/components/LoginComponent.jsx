import { useState } from "react";
import { Form, Button, Alert } from "react-bootstrap";

export default function LoginComponent() {

    const [username, setUsername] = useState('');

    const [password, setPassword] = useState('');

    const [errors, setErrors] = useState({});

    const onLoginHandler = (event) => {
        
        event.preventDefault();
        
        const formErrors = validate();
        if (Object.keys(formErrors).length > 0) {
            setErrors(formErrors);
        } else {
            setErrors({});
            console.log('Login request -> ', { username, password });
        }
        
    };

    const validate = () => {
        
        const newErrors = {};
        
        if (!username) {
            newErrors.username = 'Username is required';
        } else if (!/\S+@\S+\.\S+/.test(username)) {
            newErrors.username = 'Username must be a valid email address';
        }

        if (!password) {
            newErrors.password = 'Password is required';
        }

        return newErrors;

    }

    return (
        <div className="login-wrapper">
            <div className="login-form-container">
                <h2 className="text-center">NTD Calculator App</h2>
                <h3 className="login-title">Login</h3>
                <Form onSubmit={onLoginHandler}>
                    <Form.Group className="mb-3" controlId="formBasicUsername">
                        <Form.Label>Username</Form.Label>
                        <Form.Control
                            type="email"
                            placeholder="Enter username"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            isInvalid={!!errors.username}
                        />
                        <Form.Control.Feedback type="invalid">
                            {errors.username}
                        </Form.Control.Feedback>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicPassword">
                        <Form.Label>Password</Form.Label>
                        <Form.Control
                            type="password"
                            placeholder="Password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            isInvalid={!!errors.password}
                        />
                        <Form.Control.Feedback type="invalid">
                            {errors.password}
                        </Form.Control.Feedback>
                    </Form.Group>
                    <Button variant="primary" type="submit" className="login-button">Login</Button>
                </Form>
            </div>    
        </div>
    );
}