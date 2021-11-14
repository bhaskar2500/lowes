import {AppBar, Toolbar, Typography} from "@material-ui/core";
import React from "react";


export class Header extends React.Component {

    render() {
        return (<div>
            <header>
                <AppBar color="primary">
                    <Toolbar>
                        <Typography variant="h6">URL Shortner</Typography>
                    </Toolbar>
                </AppBar>
            </header>
        </div>)

    }
}