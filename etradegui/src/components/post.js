async function post(body, path) {
    const userName = localStorage.getItem("userName");
    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': userName
        },
        body: JSON.stringify(body)
    };
    const result = await fetch(`http://localhost:8080/${path}`, requestOptions)
    return result
}

export default post;

