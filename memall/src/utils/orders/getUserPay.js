import server from "../server"

export function getUserPay() {
    return server.post('/orders/userPay')
}
