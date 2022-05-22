import server from "../server";

export function getShoppingTendency(){
    return server.get('/shoppingCart/shoppingTendency')
}
