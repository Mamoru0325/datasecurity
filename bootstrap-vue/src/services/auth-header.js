export default function authHeader() {
  let user = JSON.parse(localStorage.getItem('user'));
  let timer = JSON.parse(localStorage.getItem('timer'));
  if (user && user.token) {
    return { Authorization: 'Bearer ' + user.token }; // for Spring Boot back-end
    // return { 'x-access-token': user.token };       // for Node.js Express back-end
  } else {
    return {};
  }
  if (timer ) {
    return { Authorization: 'Bearer ' + user.token }; // for Spring Boot back-end
    // return { 'x-access-token': user.token };       // for Node.js Express back-end
  } else {
    return {};
  }
}
