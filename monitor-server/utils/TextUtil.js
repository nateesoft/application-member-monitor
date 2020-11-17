const TextUtil = {
    convUnicode2Ascii : (text) => {
      let result = ""
      if(text===null){
        return result;
      }
      for (let i = 0; i < text.length; i += 1) {
        let code = parseInt(text.charCodeAt(i))
        if (0xe01 <= code && code <= 0xe5b) {
          result += String.fromCharCode(code - 0xd60)
        } else {
          result += String.fromCharCode(code)
        }
      }
      return result
    },
    convAscii2Unicode : (text) => {
      let result = ""
      if(text===null){
        return result;
      }
      for (let i = 0; i < text.length; i += 1) {
        let code = parseInt(text.charCodeAt(i))
        if (0xa1 <= code && code <= 0xfb) {
          result += String.fromCharCode(code + 0xd60)
        } else {
          result += String.fromCharCode(code)
        }
      }
      return result
    }
}

module.exports = TextUtil
