import axios from 'axios'
import React from 'react'

const Login = () => {
  const [login, setEmail] = React.useState('')
  const [password, setPassword] = React.useState('')

  const handleSubmit = (e) => {
    e.preventDefault()
    if (login === '' || password === '') {
      alert('Please fill all the fields')
    } else {
      axios
        .post('http://localhost:8087/api/login/signIn', {
          login,
          password,
        })
        .then((res) => {
          console.log(res.data)
          localStorage.setItem('token', JSON.stringify(res.data))
          window.location.reload()
        })
    }
  }

  return (
    <>
      <div
        style={{
          display: 'flex',
          alignItems: 'center',
          height: '400px',
          width: '400px',
          justifyContent: 'center',
          margin: 'auto',
          marginTop: '100px',
          border: '1px solid black',
          borderRadius: '10px',
        }}
      >
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label htmlFor="exampleInputEmail1" className="form-label">
              Email address
            </label>
            <input
              type="text"
              onChange={(e) => setEmail(e.target.value)}
              className="form-control"
              id="exampleInputEmail1"
              aria-describedby="emailHelp"
            ></input>
            <div id="emailHelp" className="form-text">
              We'll never share your email with anyone else.
            </div>
          </div>
          <div className="mb-3">
            <label htmlFor="exampleInputPassword1" className="form-label">
              Password
            </label>
            <input
              onChange={(e) => setPassword(e.target.value)}
              type="password"
              className="form-control"
              name="login"
              id="exampleInputPassword1"
            />
          </div>
          <div className="mb-3 form-check">
            <input
              type="checkbox"
              className="form-check-input"
              name="password"
              id="exampleCheck1"
            />
            <label className="form-check-label" htmlFor="exampleCheck1">
              Check me out
            </label>
          </div>
          <button type="submit" className="btn btn-primary">
            Submit
          </button>
        </form>
      </div>
    </>
  )
}

export default Login
