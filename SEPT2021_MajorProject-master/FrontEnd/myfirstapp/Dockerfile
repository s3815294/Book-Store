FROM node:16

COPY ["package.json", "package-lock.json*", "./"]

RUN npm install

COPY . .

RUN npm run build
ENTRYPOINT [ "npm" ]
CMD ["start"]