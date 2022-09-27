import React from 'react'
import Container from 'react-bootstrap/Container'
import Nav from 'react-bootstrap/Nav'
import Navbar from 'react-bootstrap/Navbar'
import { Button, DropdownButton } from 'react-bootstrap'

const NavBar = () => {
  const getUser = () => {
    const user = JSON.parse(localStorage.getItem('token'))

    return user
  }
  const getRole = () => {
    const users = getUser()
    console.log(users.role)
    return users.role
  }
  const logout = () => {
    localStorage.removeItem('user')
    window.location.reload()
  }

  return (
    <>
      <Navbar bg="light" expand="lg">
        {getUser() ? (
          <div
            className={'d-flex justify-content-center container-sm bg-light '}
          >
            <Navbar.Brand>
              <h1>My App</h1>
            </Navbar.Brand>

            <Nav className="me-auto">
              {getRole() === 'ROLE_ADMIN' ? (
                <>
                  <Nav.Link href="/Account">Book Admin DashBoard</Nav.Link>
                  <Nav.Link href="/ExchangeAcc">Auth Admin DashBoard</Nav.Link>

                  <Nav.Link href="/profile">Profile Settings</Nav.Link>
                  <Button
                    className={'btn btn-outline-secondary text-bg-light '}
                  >
                    Swagger Port Active
                  </Button>
                  <button className={'btn btn-primary'} onClick={logout}>
                    Logout
                  </button>
                </>
              ) : (
                <>
                  <Nav.Link href="/books">BooksD</Nav.Link>
                  <Nav.Link href="/authors">Authors</Nav.Link>
                  <Nav.Link href="/profile">Profile Settings</Nav.Link>
                </>
              )}
            </Nav>
          </div>
        ) : (
          <Container>
            <Navbar.Brand>
              <h1>Myy App</h1>
            </Navbar.Brand>
            <Nav className="me-auto">
              <>
                <Nav.Link href="/login">LOGİN</Nav.Link>
              </>
            </Nav>
          </Container>
        )}
      </Navbar>
    </>
  )
}

export default NavBar
