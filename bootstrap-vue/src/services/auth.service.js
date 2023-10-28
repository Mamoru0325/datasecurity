import axios from 'axios';

const API_URL = 'http://localhost:8080/api/auth/';

class AuthService {
  login(user) {
    return axios
      .post(API_URL + 'signin', JSON.stringify({
        email: user.email,
        password: user.password
      }), { headers: { "Content-Type": "application/json" }})
      .then(response => {
        console.log(response)
        if (response.data.accessToken) {
          console.log("response")
          localStorage.setItem('user', JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  signout() {
    localStorage.removeItem('user');
  }

  register(user) {
    return axios.post(API_URL + 'signup', JSON.stringify({
      email: user.email,
      password: user.password,
      username: user.username,
      phoneNumber: user.phoneNumber
    }), { headers: { "Content-Type": "application/json" } });
  }
}

export default new AuthService();
