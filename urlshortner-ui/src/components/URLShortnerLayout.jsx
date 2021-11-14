import React from "react";
import {
    Button,
    TextField,
    Grid,
    Paper,
    Typography,
} from "@material-ui/core";
import axios from "axios";

class URLShortnerLayout extends React.Component {
    constructor(props) {
        super(props);
        this.state = {shortUrl: "", longUrl: ""};
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({shortUrl: event.state.shortUrl, longUrl: event.state.longUrl});
    }

    async handleSubmit(event) {
        event.preventDefault();
        // const apiURL = process.env.REACT_APP_API_BASE_URL
        const apiURL = "http://localhost:8080/shorten"
        const shortUrl = await axios.post(apiURL, {"url": this.state.longUrl}
            , {
                headers: {'Access-Control-Allow-Origin': '*'}
            })
        if (shortUrl.status != 200)
            alert("URL already shortened add new one ")
        else
            this.setState({shortUrl: shortUrl.data});
    }

    render() {
        return (
            <div>
                <Grid container spacing={0} justify="center" direction="row">
                    <Grid item>
                        <Grid
                            container
                            direction="column"
                            justify="center"
                            spacing={2}
                        >
                            <Typography variant="h6">Enter long URL</Typography>

                            <Grid item>
                                <form onSubmit={this.handleSubmit}>
                                    <Grid container direction="column" spacing={2}>
                                        <Paper

                                            variant="elevation"
                                            elevation={2}
                                            className="paper"
                                        >
                                            <Grid item>
                                                <TextField
                                                    className="longURL"
                                                    type="text"
                                                    placeholder="Long URL"
                                                    fullWidth
                                                    name="longUrl"
                                                    variant="outlined"
                                                    value={this.state.longUrl}
                                                    onChange={(event) =>
                                                        this.setState({
                                                            [event.target.name]: event.target.value,
                                                        })
                                                    }
                                                    required
                                                    autoFocus
                                                />

                                            </Grid>
                                        </Paper>
                                    </Grid>
                                    <Grid item>
                                        <Button
                                            variant="contained"
                                            color="primary"
                                            type="submit"
                                            className="button-block"
                                        >
                                            Submit
                                        </Button>
                                    </Grid>
                                </form>
                            </Grid>

                        </Grid>
                    </Grid>
                </Grid>
                <Typography
                    variant="h6"> {this.state.shortUrl != "" ? `Short URL :${this.state.shortUrl}` : ""}</Typography>

            </div>
        );
    }
}

export default URLShortnerLayout