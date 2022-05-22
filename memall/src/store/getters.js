
const getters={
    id: state => state.user.info.id,
    name: state => state.user.info.name,
    role: state => state.user.info.role,
    token: state => state.user.login.token,
    adminGetPages: state => state.admin.getPages.data
}

export default getters
