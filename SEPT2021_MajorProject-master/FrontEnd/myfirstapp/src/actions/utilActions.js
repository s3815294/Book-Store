export const setLocalStorage = (key, value) => {
  if (window != undefined) {
    localStorage.setItem(key, value);
  }
};

export const getLocalStorage = (key) => {
  if (window != undefined) {
    return localStorage.getItem(key);
  } else {
    return undefined;
  }
};

export const deleteLocalStorage = (key) => {
  if (window != undefined) {
    localStorage.removeItem(key);
  }
};
