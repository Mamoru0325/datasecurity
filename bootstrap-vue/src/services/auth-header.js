export default function authHeader() {
  let user = JSON.parse(localStorage.getItem('user'));
  let timer = JSON.parse(localStorage.getItem('timer'));
  if (user && user.accessToken) {
    return { Authorization: 'Bearer ' + user.accessToken }; // for Spring Boot back-end
    // return { 'x-access-token': user.token };       // for Node.js Express back-end
  } else {
    return {};
  }
  if (timer ) {
    return { Authorization: 'Bearer ' + user.accessToken }; // for Spring Boot back-end
    // return { 'x-access-token': user.token };       // for Node.js Express back-end
  } else {
    return {};
  }
}
