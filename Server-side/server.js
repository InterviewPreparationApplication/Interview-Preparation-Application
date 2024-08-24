const express = require('express');
const config = require('config');
const jwt = require('jsonwebtoken');
const PORT = config.get("port");
const config2 = require('./config/configjwt')
const cors = require('cors')

const UserRouterHandler = require('./routes/user');
const IntviewRouteHandler = require('./routes/interview');
const AdminRouteHandler = require('./routes/admin');
const InterviewerRouteHandler = require('./routes/interviewer');
const app = express();
app.use(express.json());
app.use(cors())
app.use("/public",express.static("public"))

app.use((request, response, next) => {
    const skipTokenUrls = [
        '/user/register',
        '/user/login',
        '/interviewer/login',
        '/interviewer/register',
        '/admin/login',
        
    ];

    if (
        skipTokenUrls.findIndex((item) => {
            return request.url.startsWith(item);
        }) !== -1
    ) {
        // skip the token check
        next();
    } else {
        const token = request.headers['token'];
        //console.log(`token: ${token}`);
        if (!token) {
            response.status(401).json({ error: 'Missing token' });
        } else {
            try {
                // try validating token
                const payload = jwt.verify(token, config2.secrete);
               // console.log(payload);

                // add the payload details to the request
                request.user = {
                    id: payload['id'],
                    name: payload['name'],
                    type: payload['type'],
                };

                // go to the next call
                next();
            } catch (ex) {
                console.log(ex);
                response.status(401).json({ error: 'Invalid token' });
            }
        }
    }
});

app.use("/user", UserRouterHandler);
app.use("/interview", IntviewRouteHandler);
app.use("/interviewer", InterviewerRouteHandler);
app.use("/admin", AdminRouteHandler);

app.get("/", (req, res) => {
    res.send("Hello, the app is ready");
});

app.listen(PORT, () => {
    console.log(`Server started listening at port ${PORT}`);
});
