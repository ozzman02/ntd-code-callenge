import { Container, Row } from "react-bootstrap";

export default function FooterComponent() {
    return (
        <footer>
            <Container fluid>
                <Row className="bg-primary text-white">
                    <div className="text-center">&copy; 2024 NTD Software. All rights reserved.</div>
                </Row>
            </Container>
        </footer>
    );
}