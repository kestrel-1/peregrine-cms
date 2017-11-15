
const helpers = {
    isEmpty: function(field) {
        if(field === undefined || field === null || field === '' || field === '<p><br></p>') {
            return true
        }
        return false
    },
    areAllEmpty() {
        for(let i = 0; i < arguments.length; i++) {
            const ret = helpers.isEmpty(arguments[i])
            if(ret === false) return false
        }
        return true
    },
    pathToUrl(path) {
        let absoluteUrl = new RegExp('^(?:[a-z]+:)?//', 'i');   //Matches absolute URLs
        let file = new RegExp(/\.\w+$/, 'i')                    //Matches URL ending with a file extension
        if( absoluteUrl.test(path) === false ) {
            return file.test(path) ? path : `${path}.html`
        }
        else return path
    }
}

const helper = {
    install(vue) {
        vue.prototype.$helper = helpers
    }
}

export default helper