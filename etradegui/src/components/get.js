async function get(path) {
    const userName = localStorage.getItem("userName");
    const requestOptions = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': userName
        }
    };
    const result = await fetch(`http://localhost:8080/${path}`, requestOptions)
    console.log(result)
    return result
}

export default get;

