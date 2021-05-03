import axios from 'axios';

export default class GiphyService {
    getGiphys() {
        return axios.get('http://localhost:3001/giphys')
        .then(data => data.data)
    }

    deleteGiphy(id) {
        return axios.delete(`http://localhost:3001/giphys/delete/${id}`)
        .then(data => console.log(`Response After delete ${data}`))
        .catch(error => alert(error));
    }
}