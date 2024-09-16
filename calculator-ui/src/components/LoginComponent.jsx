import { useState } from "react";
import { Form, Button } from "react-bootstrap";

export default function LoginComponent() {

    const [username, setUsername] = useState('');

    const [password, setPassword] = useState('');

    const onLoginHandler = (event) => {
        event.preventDefault();
        console.log('Login request -> ', { username, password });
    };

    return (
        <div className="login-wrapper">
            <div className="login-form-container">
                <h2 className="text-center">NTD Calculator App</h2>
                <h3 className="login-title">Login</h3>
                <Form onSubmit={onLoginHandler}>
                    <Form.Group className="mb-3" controlId="formBasicUsername">
                        <Form.Label>Email address</Form.Label>
                        <Form.Control
                            type="email"
                            placeholder="Enter username"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                        />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicPassword">
                        <Form.Label>Password</Form.Label>
                        <Form.Control
                            type="password"
                            placeholder="Password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </Form.Group>
                    <Button variant="primary" type="submit" className="login-button">Login</Button>
                </Form>
            </div>    
        </div>
    );
}