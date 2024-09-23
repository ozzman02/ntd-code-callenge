/* eslint-disable react/prop-types */
import { useState } from "react";
import { Button, Modal, ListGroup, Navbar, Container, Nav } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import useAuthorizationContext from '../hooks/UseAuthorizationContext';

export default function HeaderComponent({ username }) {

    const { userLogout } = useAuthorizationContext();

    const [showModal, setShowModal] = useState(false);

    const navigator = useNavigate();

    const onShowModalHandler = () => {
      setShowModal(true);
    };

    const onCloseModalHandler = () => {
      setShowModal(false);
    };

    const onLogoutHandler = () => {
      userLogout();
      navigator('/');
    };

    return (
      <Navbar bg="dark" data-bs-theme="dark">
        <Container>
          <Navbar.Brand href="#home">NTD Software Calculator</Navbar.Brand>
          <Nav className="header-right" activeKey="/home">
            <Nav.Link href="/home">Home</Nav.Link>
            <Nav.Link href="#instructions" onClick={onShowModalHandler}>Instructions</Nav.Link>
            <Nav.Link href="#currentUser" eventKey="disabled" disabled><i>{username}</i></Nav.Link>
            <Nav.Link href="/logout" onClick={onLogoutHandler}>Logout</Nav.Link>
          </Nav>
        </Container>
        <Modal
          show={showModal}
          onHide={onCloseModalHandler}
          backdrop="static"
          keyboard={false}
        >
          <Modal.Header closeButton>
            <Modal.Title>Calculator Instructions</Modal.Title>
            </Modal.Header>
              <Modal.Body>
              <ListGroup as="ol" numbered>
                <ListGroup.Item as="li">
                  Valid operators: <b><i>+, -, /, *, sqrt()</i></b>. You can use parenthesis () as well. </ListGroup.Item>
                <ListGroup.Item as="li">
                  If the length of the math exp is &gt; 1 and &lt; 20 is a <b><i>Standard</i></b> math expression. 
                  The cost is <b>$1</b>.
                </ListGroup.Item>
                <ListGroup.Item as="li">
                  If the length of the math exp is &gt; 20 and &lt; 100 is a <b><i>Complex</i></b> math expression. 
                  The cost is <b><i>$5</i></b>.
                </ListGroup.Item>
                <ListGroup.Item as="li">
                  You can generate a random math expression using the <b><i>Generate Mathematical Expression </i></b>button. 
                  This will be an <b><i>Special</i></b> math expression and the cost is <b><i>$10</i></b>. 
                </ListGroup.Item>
                <ListGroup.Item as="li">
                  If an operation contains unsupported operators or throws an error the result will be an <b><i>Invalid</i></b> math expression. 
                  There is no charge for this operation type.
                </ListGroup.Item>
                <ListGroup.Item as="li">
                  There is an implicit multiplication operation when using the <b><i>sqrt()</i></b> operation next to another operand. 
                  For example: <b><i>10+10sqrt(25)</i></b> is equivalent to <b><i>10+10*sqrt(25)</i></b>.
                </ListGroup.Item>
              </ListGroup>
              </Modal.Body>
            <Modal.Footer>
            <Button variant="secondary" onClick={onCloseModalHandler}>Close</Button>
          </Modal.Footer>
        </Modal>
      </Navbar>
    );
}