FROM node:16 as builder
WORKDIR /client
COPY ./package.json .
RUN npm i
RUN npm cache clear --force
COPY ./ ./
RUN npm run build

FROM nginx
EXPOSE 80

RUN rm /etc/nginx/nginx.conf

COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=builder /client/build /usr/share/nginx/html
