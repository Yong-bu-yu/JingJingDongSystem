import server from "../server";

export function Insert(param){
    return server.post('/admin/insert',param)
}
